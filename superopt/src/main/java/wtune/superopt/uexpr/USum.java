package wtune.superopt.uexpr;

import java.util.Set;

import static wtune.common.utils.IterableSupport.all;
import static wtune.superopt.uexpr.UVar.VarKind.BASE;

public interface USum extends UUnary {
  @Override
  default UKind kind() {
    return UKind.SUMMATION;
  }

  Set<UVar> boundedVars();

  static USum mk(Set<UVar> sumVars, UTerm body) {
    assert all(sumVars, it -> it.kind() == BASE);

    if (body.kind() == UKind.MULTIPLY) return new USumImpl(sumVars, body);
    else return new USumImpl(sumVars, UMul.mk(body));
  }
}
