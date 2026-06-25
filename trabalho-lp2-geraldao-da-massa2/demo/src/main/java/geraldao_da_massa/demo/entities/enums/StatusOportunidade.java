package geraldao_da_massa.demo.entities.enums;

public enum StatusOportunidade {
    RASCUNHO,               // criada mas não submetida
    AGUARDANDO_APROVACAO,   // submetida, aguardando docente aprovar
    APROVADA,               // docente aprovou, aberta para inscrições
    REPROVADA,              // docente reprovou
    EM_INSCRICOES,          // período de inscrições aberto
    EM_EXECUCAO,            // atividade em andamento
    CONCLUIDA,              // encerrada, pronta para certificação
    CANCELADA,              // cancelada por docente/coordenador
    ARQUIVADA               // arquivada
}
