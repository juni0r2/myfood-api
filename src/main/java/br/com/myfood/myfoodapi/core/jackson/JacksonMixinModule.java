package br.com.myfood.myfoodapi.core.jackson;

import br.com.myfood.myfoodapi.api.mode.mixin.CidadeMixin;
import br.com.myfood.myfoodapi.api.mode.mixin.CozinhaMixin;
import br.com.myfood.myfoodapi.api.mode.mixin.RestaurenteMixin;
import br.com.myfood.myfoodapi.domain.model.Cidade;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestaurenteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
