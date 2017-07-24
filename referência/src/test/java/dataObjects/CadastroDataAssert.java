package dataObjects;

import org.assertj.core.api.AbstractAssert;
import org.hamcrest.core.Is;
import work.assisjrs.referencia.Usuario;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CadastroDataAssert extends AbstractAssert<CadastroDataAssert, CadastroData> {
    public CadastroDataAssert(CadastroData actual) {
        super(actual, CadastroDataAssert.class);
    }

    public CadastroDataAssert existe(final String usuario, final String email) throws Throwable {
        //final String nome = actual.findByEmail(email);

        //assertThat(nome, Is.is(usuario));

        final Usuario one = actual.findOne(1L);

        assertNotNull(one);

        return this;
    }
}
