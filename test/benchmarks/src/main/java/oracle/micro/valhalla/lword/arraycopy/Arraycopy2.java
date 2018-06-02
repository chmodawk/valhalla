/*
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package oracle.micro.valhalla.lword.arraycopy;

import oracle.micro.valhalla.ArraycopyBase;
import oracle.micro.valhalla.lword.types.Value2;
import oracle.micro.valhalla.types.Total;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

public class Arraycopy2 extends ArraycopyBase {


    @State(Scope.Thread)
    public static class StateValue {
        Value2[] src;
        Value2[] dst;

        @Setup
        public void setup() {
            src = new Value2[size];
            for (int i = 0, k = 0; i < src.length; i++, k += 2) {
                src[i] = Value2.of(k, k + 1);
            }
            dst = new Value2[size];
        }
    }


    @Benchmark
    public Object loopValue(StateValue st) {
        Value2[] src = st.src;
        Value2[] dst = st.dst;
        for (int i = 0; i < size; i++) {
            dst[i] = src[i];
        }
        return dst;
    }

    @Benchmark
    public Object copyValue(StateValue st) {
        System.arraycopy(st.src, 0, st.dst, 0, size);
        return st.dst;
    }

}
