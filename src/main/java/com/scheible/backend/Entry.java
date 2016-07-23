package com.scheible.backend;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author sj
 */
@Entity
public class Entry {

	private final UUID surrogateId = UUID.randomUUID();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
    
    @Column(length = 4096)
    private String description;
	
    @Column(length = 512)
    private String url;	

	public Entry() {
	}

	public void setName(String name) {
		this.name = name;
	}

    public void setDescription(String description) {
        this.description = description;
    }

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		} else if (obj != null && getClass().equals(obj.getClass())) {
			Entry other = (Entry) obj;
			return id != 0 ? id == other.id : surrogateId == other.surrogateId;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id != 0 ? Long.valueOf(id).hashCode() : surrogateId.hashCode();
	}
	
	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}
	
    public String getDescription() {
        return description;
    }

	public long getId() {
		return id;
	}
}
