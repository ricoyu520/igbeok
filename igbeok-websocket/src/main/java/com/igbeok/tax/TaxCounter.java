package com.igbeok.tax;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TaxCounter {
	private static final String[] labels = { "税前收入", "公积金缴费比例(0.xx)" };
	private static final float RESERVED_FOUND_TOP = 120000f;// 公积金缴费上限
	// 各级税率
	private static final float LEVEL_ONE_RATE = 0.03f;
	private static final float LEVEL_TWO_RATE = 0.1f;
	private static final float LEVEL_THREE_RATE = 0.20f;
	private static final float LEVEL_FOUR_RATE = 0.25f;
	private static final float LEVEL_FIVE_RATE = 0.3f;
	private static final float LEVEL_SIX_RATE = 0.35f;
	private static final float LEVEL_SEVEN_RATE = 0.45f;
	// 纳税级别
	private static final float LEVEL_ONE = 0;
	private static final float LEVEL_TWO = 1500f;
	private static final float LEVEL_THREE = 4500f;
	private static final float LEVEL_FOUR = 9000f;
	private static final float LEVEL_FIVE = 35000f;
	private static final float LEVEL_SIX = 55000f;
	private static final float LEVEL_SEVEN = 80000f;
	
	private static float socialTaxRatePersonal = 0.18f;// 公积金缴费比例默认是18%
	private static float socialTaxRateCompany = 0.28f;// 公司公积金缴费比例默认是28%
	private static float baseLine = 0f; //缴费基数， 默认工资总额

	private static final float CUTOFF_POINT = 3500f; // 个税起征点

	public static void main(String[] args) {
		TaxCounter tax = new TaxCounter();
		float income = tax.getInput(args, 0);
		socialTaxRatePersonal = tax.getInput(args, 1);
		float[] taxs = tax.caculateTax(income, socialTaxRatePersonal);
		System.out.println("税后收入:" + taxs[0]);
		System.out.println("公积金:" + taxs[1]);
		System.out.println("共缴纳个税:" + taxs[2]);
		System.out.println("在一级纳税:" + taxs[3]);
		System.out.println("在二级纳税:" + taxs[4]);
		System.out.println("在三级纳税:" + taxs[5]);
		System.out.println("在四级纳税:" + taxs[6]);
		System.out.println("在五级纳税:" + taxs[7]);
		System.out.println("在六级纳税:" + taxs[8]);
		System.out.println("在七级纳税:" + taxs[9]);
	}

	/**
	 * 根据用户输入, 获取用户的税前收入
	 * 
	 * @param args
	 * @return
	 */
	private float getInput(String[] args, int index) {
		Float finalInput = null;

		if (args.length > index) {
			try {
				finalInput = Float.valueOf(args[index]);
			} catch (NumberFormatException e) {
				System.out.println("提供的" + labels[index] + "应该是合法的数字");
			}
		}
		// 有输入就直接返回
		if (finalInput != null) {
			return finalInput;
		}

		// 没有输入或者输入错误
		// StringBuilder incomeStr = new StringBuilder();
		while (null == finalInput) {
			System.out.print("请提供您的" + labels[index] + ": ");
			// 等待用户输入月收入是多少
			Scanner in = new Scanner(System.in);

			// 将用户的输入转成数字, 如果不合法,继续循环
			try {
				finalInput = in.nextFloat();
				System.out.println("再次输入后得到的值为: " + finalInput);
				break;
			} catch (InputMismatchException e) {
				System.out.println("提供的" + labels[index] + "应该是合法的数字");
				continue;
			}
		}
		return finalInput;
	}

	public float[] caculateTax(float income, float socialTaxRate) {
		// 这个是交掉的公积金,如果超过了最高缴费基数, 则按最高缴费基数来计算
		float reservedFound = (income > RESERVED_FOUND_TOP ? RESERVED_FOUND_TOP : income) * socialTaxRate;
		// 扣除公积金和起征点后需要缴税的那部分money
		float taxedIncome = income - reservedFound - CUTOFF_POINT;

		float finalIncome = 0f;// 最终的税后收入
		float tax = 0f;// 最终要交的税
		float tax1 = 0f;// 一级交的税
		float tax2 = 0f;// 二级交的税
		float tax3 = 0f;
		float tax4 = 0f;
		float tax5 = 0f;
		float tax6 = 0f;
		float tax7 = 0f;

		// 计算一级纳税额
		if ((LEVEL_ONE < taxedIncome && LEVEL_TWO >= taxedIncome)) {
			tax1 = taxedIncome * LEVEL_ONE_RATE;
		} else if (taxedIncome > LEVEL_TWO) {
			tax1 = LEVEL_TWO * LEVEL_ONE_RATE;
		}
		// 计算二级纳税额
		if (taxedIncome > LEVEL_TWO && taxedIncome <= LEVEL_THREE) {
			tax2 = (taxedIncome - LEVEL_TWO) * LEVEL_TWO_RATE;
		} else if (taxedIncome > LEVEL_THREE) {
			tax2 = (LEVEL_THREE - LEVEL_TWO) * LEVEL_TWO_RATE;
		}
		// 计算三级纳税额
		if (taxedIncome > LEVEL_THREE && taxedIncome <= LEVEL_FOUR) {
			tax3 = (taxedIncome - LEVEL_THREE) * LEVEL_THREE_RATE;
		} else if (taxedIncome > LEVEL_FOUR) {
			tax3 = (LEVEL_FOUR - LEVEL_THREE) * LEVEL_THREE_RATE;
		}
		// 计算四级纳税额
		if (taxedIncome > LEVEL_FOUR && taxedIncome <= LEVEL_FIVE) {
			tax4 = (taxedIncome - LEVEL_FOUR) * LEVEL_FOUR_RATE;
		} else if (taxedIncome > LEVEL_FIVE) {
			tax4 = (LEVEL_FIVE - LEVEL_FOUR) * LEVEL_FOUR_RATE;
		}
		// 计算五级纳税额
		if (taxedIncome > LEVEL_FIVE && taxedIncome <= LEVEL_SIX) {
			tax5 = (taxedIncome - LEVEL_FIVE) * LEVEL_FIVE_RATE;
		} else if (taxedIncome > LEVEL_SIX) {
			tax5 = (LEVEL_SIX - LEVEL_FIVE) * LEVEL_FIVE_RATE;
		}
		// 计算六级纳税额
		if (taxedIncome > LEVEL_SIX && taxedIncome <= LEVEL_SEVEN) {
			tax6 = (taxedIncome - LEVEL_SIX) * LEVEL_SIX_RATE;
		} else if (taxedIncome > LEVEL_SEVEN) {
			tax6 = (LEVEL_SEVEN - LEVEL_SIX) * LEVEL_SIX_RATE;
		}

		if (taxedIncome > LEVEL_SEVEN) {
			tax7 = (taxedIncome - LEVEL_SEVEN) * LEVEL_SEVEN_RATE;
		}
		tax = tax1 + tax2 + tax3 + tax4 + tax5 + tax6 + tax7;// 一共要交的税
		finalIncome = income - reservedFound - tax;// 税后收入= 税前收入-交掉的公积金-交掉的个税
		return new float[] { finalIncome, reservedFound, tax, tax1, tax2, tax3, tax4, tax5, tax6, tax7 };
	}
}
