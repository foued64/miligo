package fr.miligo.model.dao;

public final class RequetesDaoBorne {

	public static final String FIND_BORNES_BY_GSBDD = "select b from Borne b join b.site s join s.gsbdd g where g.id =:idGsbdd";

}
