package me.edurevsky.controleescola.forms;

import lombok.Data;
import me.edurevsky.controleescola.entities.AppUser;
import me.edurevsky.controleescola.entities.Role;
import me.edurevsky.controleescola.validation.AlreadyRegisteredUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
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

    public static AppUser convertToAppUser(UsuarioForm usuarioForm) {
        AppUser appUser = new AppUser();
        appUser.setUsername(usuarioForm.getUsername());
        appUser.setPassword(usuarioForm.getPassword());
        appUser.setRoles(usuarioForm.getRoles());
        appUser.setCompleteName(usuarioForm.getCompleteName());
        return appUser;
    }
}
