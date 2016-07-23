package com.scheible.backend;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author sj
 */
@Entity
public class SearchResult {
	
	private final UUID surrogateId = UUID.randomUUID();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String query;

	@ManyToMany
	@JoinTable(
			name = "EntrySearchResult",
			joinColumns = @JoinColumn(name = "SEARCH_RESULT_ID", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "ENTRY_ID", referencedColumnName = "ID"))
	private Set<Entry> entries;
	
	public SearchResult() {
	}

	public long getId() {
		return id;
	}	

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Set<Entry> getEntries() {
		return entries;
	}

	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		} else if (obj != null && getClass().equals(obj.getClass())) {
			SearchResult other = (SearchResult) obj;
			return id != 0 ? id == other.id : surrogateId == other.surrogateId;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id != 0 ? Long.valueOf(id).hashCode() : surrogateId.hashCode();
	}	
}
