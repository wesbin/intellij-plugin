/*
 * Copyright 2000-2015 JetBrains s.r.o.
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
package com.intellij.database.model;

import com.intellij.database.util.Case;
import com.intellij.database.util.Casing;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author gregsh
 */
public interface CasingProvider {

  @NotNull
  Casing getCasing(@NotNull ObjectKind kind, @Nullable DasObject context);


  Casing EXACT_CASING = Casing.create(Case.EXACT, Case.EXACT);
  Casing UPPER_CASING = Casing.create(Case.UPPER, Case.EXACT);
  Casing PLAIN_LOWER_CASING = Casing.create(Case.LOWER, Case.EXACT);
  Casing LOWER_CASING = Casing.create(Case.LOWER, Case.LOWER);
  Casing MIXED_CASING = Casing.create(Case.MIXED, Case.MIXED);
  Casing UNKNOWN_CASING = Casing.create(Case.MIXED, Case.EXACT);
}
