package me.edurevsky.controleescola.forms;

import me.edurevsky.controleescola.entities.Role;
import me.edurevsky.controleescola.validation.AlreadyRegisteredUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class UsuarioForm {

    @NotBlank(message = "O nome não pode estar em branco")
    private String completeName;

    @NotBlank(message = "O nome de usuário não pode estar em branco")
    @AlreadyRegisteredUsername
    private String username;

    @NotBlank(message = "A senha não pode estar em branco")
    private String password;

    @NotEmpty(message = "É preciso selecionar ao menos uma autorização")
    private Set<Role> roles;


    public UsuarioForm(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}