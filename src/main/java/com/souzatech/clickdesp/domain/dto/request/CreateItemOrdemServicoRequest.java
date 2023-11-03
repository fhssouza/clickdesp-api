package com.souzatech.clickdesp.domain.dto.request;

import com.souzatech.clickdesp.domain.model.ItemOrdemServico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemOrdemServicoRequest {

    private Integer quantidade;
    private Long servico;

//    public static List<CreateItemOrdemServicoRequest> converterRequest(List<ItemOrdemServico> itens) {
//        return itens.stream().map(item -> {
//            return new CreateItemOrdemServicoRequest(item.getQuantidade(), item.getServico().getId());
//        }).collect(Collectors.toList());
//    }


    public static List<ItemOrdemServico> converter(List<CreateItemOrdemServicoRequest> itens) {
        return itens.stream().map(ItemOrdemServico::new).collect(Collectors.toList());
    }


//    var postResponseList = list.stream()
////                .map(post -> PostResponse.fromEntity(post))
//            .map(PostResponse::fromEntity) //metodo de referencia
//            .collect(Collectors.toList());

//    public static PostResponse fromEntity(Post post){
////        var response = new PostResponse();
////        response.setText(post.getText());
////        response.setDateTime(post.getDateTime());
////        return response;
////    }


}
