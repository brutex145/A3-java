public class UsuarioSistema {

    private String login;
    private String senha;
    private boolean superusuario;

    public UsuarioSistema(String login, String senha, boolean superusuario) {
        this.login = login;
        this.senha = senha;
        this.superusuario = superusuario;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isSuperusuario() {
        return superusuario;
    }

    public void setSuperusuario(boolean superusuario) {
        this.superusuario = superusuario;
    }

    @Override
    public String toString() {
        return "Usuario [login: " + login + ", senha: " + senha + ", superusuario: " + superusuario + "]";
    }
}

