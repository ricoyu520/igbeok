package com.igbeok.hibernate.common.orm.hibernate.transform;

import org.hibernate.transform.ResultTransformer;

public class CustomTransformers {

	public static ResultTransformer underlineStyleTransformer(Class<?> target){
		return new UnderlineStyleResultTransformer(target);
	}
}
