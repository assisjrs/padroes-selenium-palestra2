package pageObjects;

import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.openqa.selenium.By.xpath;

public class CadastroPageAssert extends AbstractAssert<CadastroPageAssert, CadastroPage> {

    public CadastroPageAssert(CadastroPage page) {
        super(page, CadastroPageAssert.class);
    }

    public CadastroPageAssert quantidaDeUsuarios(int quantidadeUsuariosCadastrados) {
        assertThat(actual.getUsuarios().size()).isEqualTo(quantidadeUsuariosCadastrados);

        return this;
    }

    public CadastroPageAssert existe(final String usuario) {
        boolean encontrou = false;

        for (final WebElement tr: actual.getUsuarios()) {
            for (final WebElement td: tr.findElements(xpath("td[2]"))) {
                final String text = td.getText();

                if(usuario.equalsIgnoreCase(text))
                    encontrou = true;
            }
        }

        if(!encontrou)
            fail(String.format("Não foi possível encontrar o usuário: %s", usuario));

        return this;
    }
}
