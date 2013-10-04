package com.igbeok.hibernate.common.orm.hibernate.transform;

import com.igbeok.hibernate.common.orm.hibernate.transform.convertor.ColumnNameConvertor;
import com.igbeok.hibernate.common.orm.hibernate.transform.convertor.impl.UnderlineStyleColumnNameConvertor;

/**
 * 
 * @author ricoyu
 * 
 */
@SuppressWarnings("serial")
public class UnderlineStyleResultTransformer extends AbstractFieldNameToBeanResultTransformer {

	public UnderlineStyleResultTransformer(Class<?> resultClass) {
		super(resultClass);
	}

	@Override
	protected ColumnNameConvertor getConvertorInstance() {
		return UnderlineStyleColumnNameConvertor.getInstance();
	}

}
