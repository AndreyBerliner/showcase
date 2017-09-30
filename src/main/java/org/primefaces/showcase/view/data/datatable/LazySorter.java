/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.view.data.datatable;

import java.util.Comparator;
import org.primefaces.model.SortOrder;
import org.primefaces.showcase.domain.Car;

public class LazySorter implements Comparator<Car> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(Car car1, Car car2) {
        System.out.println("-- before try sortField="+sortField);
            System.out.println("-- before try sortOrder="+sortOrder.toString());
        try {
            Object value1 = Car.class.getField(this.sortField).get(car1);
            System.out.println("-- value1="+value1.toString());
            Object value2 = Car.class.getField(this.sortField).get(car2);
            System.out.println("-- value2="+value2.toString());

            int value = ((Comparable)value1).compareTo(value2);
            System.out.println("-- value="+value);
            System.out.println("-- SortOrder="+(SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value));
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
