/*
 * Copyright 2020 Nicola Atzei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 *
 */
package xyz.balzaclang.xsemantics;

import xyz.balzaclang.balzac.BalzacFactory;
import xyz.balzaclang.balzac.TypeVariable;

/**
 * @author bettini
 *
 */
public class LambdaUtils {

    protected int counter = 0;

    public void resetCounter() {
        counter = 0;
    }

    public TypeVariable createTypeVariable(String name) {
        TypeVariable typeVariable = BalzacFactory.eINSTANCE.createTypeVariable();
        typeVariable.setValue(name);
        return typeVariable;
    }

    public TypeVariable createFreshTypeVariable() {
        return createTypeVariable("X" + counter++);
    }

}
