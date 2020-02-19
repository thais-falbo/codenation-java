package challenge;

public class CriptografiaCesariana implements Criptografia {
    @Override
    public String criptografar(String texto) {
        return transformarTexto(texto, 3);
    }

    @Override
    public String descriptografar(String texto) {
        return transformarTexto(texto, -3);
    }

    private String transformarTexto(String texto, int rotacao) {
        validarTexto(texto);

        StringBuilder resultado = new StringBuilder(texto.length());
        char[] codigosASCII = texto.toLowerCase().toCharArray();

        for(int codigo : codigosASCII) {
            char novoCaractere = (char) codigo;

            if (!Character.isDigit(codigo) && Character.isLetter(codigo))
                novoCaractere = (char) (codigo + rotacao);

            resultado.append(novoCaractere);
        }

        return resultado.toString();
    }

    private void validarTexto(String texto) {
        if (texto == null)
            throw new NullPointerException();
        else if (texto.isEmpty())
            throw new IllegalArgumentException();
    }
}
