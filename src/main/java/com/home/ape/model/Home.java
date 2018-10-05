/*
 * 
 */
package com.home.ape.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Home.
 */
@Entity
@Table(name = "APE_HOME")
@Getter
@Setter
@ToString(exclude = { "shortName", "iconUrl", "imageUrl", "description" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Home extends BaseObject {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -2747042857222372028L;

	/** The name. */
	private String				name;

	/** The short name. */
	private String				shortName;

	/** The icon url. */
	private String				iconUrl;

	/** The description. */
	private String				description;

	/** The image url. */
	private String				imageUrl;

}
