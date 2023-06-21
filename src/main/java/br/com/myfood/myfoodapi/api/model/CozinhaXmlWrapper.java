package br.com.myfood.myfoodapi.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
// import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import br.com.myfood.myfoodapi.domain.model.Cozinha;
import lombok.Data;
import lombok.NonNull;

// @JacksonXmlRootElement(localName = "cozinhas") Tambem funciona
@JsonRootName(value = "cozinhas")
@Data
public class CozinhaXmlWrapper {

    // @JacksonXmlProperty(localName = "cozinha")
    @JsonProperty(value = "cozinha")
    // @JacksonXmlElementWrapper(useWrapping = false)
    @NonNull
    private List<Cozinha> cozinhas;
}
