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

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface PsiObject extends DasObject, PsiElement, DasScopeAware {
  @Override
  @NotNull
  default JBIterable<? extends DasObject> getDasChildren(@Nullable ObjectKind kind) {
    return JBIterable.empty();
  }

  @Nullable
  @Override
  default DasObject getDasParent() {
    return null;
  }

  @Override
  @Nullable
  default String getComment() {
    return null;
  }
}