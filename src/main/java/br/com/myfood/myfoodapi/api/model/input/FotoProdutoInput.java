package br.com.myfood.myfoodapi.api.model.input;

import br.com.myfood.myfoodapi.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoInput {

    @NotNull
    @FileSize(max= "20KB")
    private MultipartFile arquivo;

    @NotBlank
    private String descricao;
}
