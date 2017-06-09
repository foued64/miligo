package fr.miligo.common;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.entetrs.commons.uuid.GeneratedUUID;
import net.entetrs.commons.uuid.UUIDRepresentation;

@SuppressWarnings("serial")
@MappedSuperclass
// Annotations LomBok
@Getter
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
