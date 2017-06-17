package fr.miligo.common;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.entetrs.commons.uuid.GeneratedUUID;
import net.entetrs.commons.uuid.UUIDRepresentation;

@SuppressWarnings("serial")
@MappedSuperclass
// Annotations LomBok
@Getter
/**
 * J'ai rajouter le setter pour pouvoir faire les tests avec la borne.SEB
 */
@Setter
@EqualsAndHashCode(of = "id")
public abstract class AbstractEntity implements Serializable {

	// Champs techniques

	@Inject
	@Id
	@GeneratedUUID(representation = UUIDRepresentation.BASE64_STRING)
	@Column(length = 24)
	// 24 octets pour stocker un UUID 128 bits en base 64
	private String id;

}
