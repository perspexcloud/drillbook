/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.drill.contrib.function;

import org.apache.drill.exec.expr.DrillAggFunc;
import org.apache.drill.exec.expr.annotations.FunctionTemplate;
import org.apache.drill.exec.expr.annotations.Output;
import org.apache.drill.exec.expr.annotations.Param;
import org.apache.drill.exec.expr.annotations.Workspace;
import org.apache.drill.exec.expr.holders.NullableFloat8Holder;

@FunctionTemplate(name = "summy", 
		scope = FunctionTemplate.FunctionScope.POINT_AGGREGATE, nulls = FunctionTemplate.NullHandling.INTERNAL)
public class AggregateFunctions implements DrillAggFunc {

	@Param
	NullableFloat8Holder input;

	@Output
	NullableFloat8Holder out;

	@Workspace
	NullableFloat8Holder prev;

	public void setup() {

	}
	
	@Override
	public void add() {
		prev.value += input.value;
	}

	@Override
	public void output() {
		out.value = 17;
		out.isSet = 1;
//		out.value = prev.value;
	}

	@Override
	public void reset() {
		prev.value = 0;
	}
}