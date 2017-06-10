package pageObjects;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class CadastroPageAssert extends AbstractAssert<CadastroPageAssert, CadastroPage> {

    public CadastroPageAssert(CadastroPage page) {
        super(page, CadastroPageAssert.class);
    }

    public void quantidaDeUsuarios(int quantidadeUsuariosCadastrados) {
        assertThat(actual.getUsuarios().size()).isEqualTo(quantidadeUsuariosCadastrados);
    }
}
