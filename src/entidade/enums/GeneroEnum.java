package entidade.enums;

public enum GeneroEnum {
	Açao("Ação"),
	Animaçao("Animação"),
	Aventura("Aventura"),
	Comedia("Comédia"),
	Crime("Crime"),
	Documentario("Documentário"),
	Drama("Drama"),
	Familia("Família"),
	Fantasia("Fantasia"),
	FicçaoCientifica("Ficção Científica"),
	Guerra("Guerra"),
	Historia("História"),
	Horror("Horror"),
	Musical("Musical"),
	Misterio("Mistério"),
	Romance("Romance"),
	Suspense("Suspense"),
	Thriller("Thriller"),
	Western("Western"),
	Biopic("Biopic"),
	Historico("Histórico");
	
	private String descricao;

	 GeneroEnum(String descricao) {
		this.descricao = descricao;
	}

	 public String getDescricao() {
		 return descricao;
	 }
	 
	@Override
	public String toString() {
		return this.getDescricao();
	}
	 public  GeneroEnum fromDescricao(String descricao) {
		 if(descricao.isBlank() || descricao.isEmpty()) {
			 throw new IllegalArgumentException("A descrição esta vazia"); 
		 }
	        for (GeneroEnum genero : GeneroEnum.values()) {
	            if (genero.getDescricao().equalsIgnoreCase(descricao)) {
	                return genero;
	            }
	        }
	        throw new IllegalArgumentException("Gênero não encontrado para a descrição: " + descricao);
	    }
	

	
	 
}
