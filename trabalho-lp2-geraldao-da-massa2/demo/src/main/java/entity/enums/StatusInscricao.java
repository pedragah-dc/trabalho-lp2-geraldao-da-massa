package entity.enums;

public enum StatusInscricao {
    PENDENTE,       // aguardando aprovação do responsável
    APROVADO,       // responsável aprovou
    REJEITADO,      // responsável rejeitou
    CANCELADO,      // discente cancelou
    SUBSTITUIDO     // discente foi substituído por outro
}
