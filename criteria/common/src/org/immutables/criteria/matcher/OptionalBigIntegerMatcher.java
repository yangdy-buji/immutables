/*
 * Copyright 2019 Immutables Authors and Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.immutables.criteria.matcher;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

/**
 * Intersection type between {@link OptionalMatcher} and {@link NumberMatcher}.
 *
 * <p>Syntax sugar to avoid chaining {@code value()} method from {@link OptionalMatcher}
 * on long expressions with many optional elements.
 *
 * @param <R> root criteria type
 */
public interface OptionalBigIntegerMatcher<R> extends OptionalNumberMatcher<R, BigInteger> {

  /**
   * Self-type for this matcher
   */
  interface Self extends Template<Self>, Disjunction<Template<Self>> {}

  interface Template<R> extends OptionalBigIntegerMatcher<R>, WithMatcher<R, Self>,
          NotMatcher<R, Self>, Projection<Optional<BigInteger>>, Aggregation.NumberTemplate<Optional<BigInteger>, Optional<BigInteger>, Optional<BigDecimal>> {}

  /**
   * Similar to main {@link OptionalBigIntegerMatcher.Template} but with {@code @Nullable} projections and aggregations
   */
  interface NullableTemplate<R> extends OptionalBigIntegerMatcher<R>, WithMatcher<R, Self>,
          NotMatcher<R, Self>, Projection<BigInteger>, Aggregation.NumberTemplate<BigInteger, BigInteger, BigDecimal> {}


  @SuppressWarnings("unchecked")
  static <R> CriteriaCreator<R> creator() {
    class Local extends AbstractContextHolder implements Self {
      private Local(CriteriaContext context) {
        super(context);
      }
    }

    return ctx -> (R) new Local(ctx);
  }

}