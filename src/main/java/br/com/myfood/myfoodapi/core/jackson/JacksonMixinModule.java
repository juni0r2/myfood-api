package br.com.myfood.myfoodapi.core.jackson;

import br.com.myfood.myfoodapi.api.model.mixin.CidadeMixin;
import br.com.myfood.myfoodapi.api.model.mixin.CozinhaMixin;
import br.com.myfood.myfoodapi.domain.model.Cidade;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
