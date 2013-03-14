package se.cambio.cds.gdl.model.expression;

import java.util.ArrayList;
import java.util.List;

import se.cambio.cds.gdl.model.expression.BinaryExpression;
import se.cambio.cds.gdl.model.expression.ConstantExpression;
import se.cambio.cds.gdl.model.expression.ExpressionItem;
import se.cambio.cds.gdl.model.expression.FunctionalExpression;
import se.cambio.cds.gdl.model.expression.OperatorKind;
import se.cambio.cds.gdl.model.expression.Variable;

import junit.framework.TestCase;

public class FunctionalExpressionTest extends TestCase {
	
	public void testCreateSimpleFunctionalExpression() {
		FunctionalExpression fe = FunctionalExpression.create("today");
		assertEquals("today()", fe.toString());
	}
	
	public void testCreateSimpleFunctionalExpressionWithSingleVariable() {
		FunctionalExpression fe = FunctionalExpression.create("sin",
				ConstantExpression.create("180"));
		assertEquals("sin(180)", fe.toString());
	}
	
	public void testCreateFunctionalExpressionWithNestedVariables() {
		List<ExpressionItem> items = new ArrayList<ExpressionItem>();
		items.add(ConstantExpression.create("180"));
		BinaryExpression be1 = BinaryExpression.create(
				Variable.createByCode("gt0001"),
				ConstantExpression.create("2"), 
				OperatorKind.MULTIPLICATION);				
		items.add(be1);
		FunctionalExpression fe = FunctionalExpression.create("max",
				items);
		assertEquals("max(180,($gt0001*2))", fe.toString());
	}
}
/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 2.0/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  2.0 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *
 *  The Initial Developers of the Original Code are Iago Corbal and Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2012-2013
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */