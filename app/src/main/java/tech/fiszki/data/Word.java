package tech.fiszki.data;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@Entity(nameInDb = "word")
public class Word {


	@Id(autoincrement = true)
	@Property(nameInDb = "word_id")
	private Long id;

	private String originalWord;
	private String translatedWord;
	private String language;

	@ToMany(referencedJoinProperty = "wordId")
	private List<Association> associations;
	@ToMany(referencedJoinProperty = "wordId")
	private List<tech.fiszki.data.Repetition> repetitions;

	/** Used to resolve relations */
	@Generated(hash = 2040040024)
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	@Generated(hash = 768131649)
	private transient WordDao myDao;



	@Generated(hash = 1755145058)
	public Word(Long id, String originalWord, String translatedWord,
			String language) {
		this.id = id;
		this.originalWord = originalWord;
		this.translatedWord = translatedWord;
		this.language = language;
	}

	@Generated(hash = 3342184)
	public Word() {
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalWord() {
		return originalWord;
	}

	public String getTranslatedWord() {
		return translatedWord;
	}

	public String getLanguage() {
		return language;
	}

	public void setOriginalWord(String originalWord) {
		this.originalWord = originalWord;
	}

	public void setTranslatedWord(String translatedWord) {
		this.translatedWord = translatedWord;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}

	public void setRepetitions(List<Repetition> repetitions) {
		this.repetitions = repetitions;
	}

	/**
	 * To-many relationship, resolved on first access (and after reset).
	 * Changes to to-many relations are not persisted, make changes to the target entity.
	 */
	@Generated(hash = 525472562)
	public List<Association> getAssociations() {
		if (associations == null) {
			final DaoSession daoSession = this.daoSession;
			if (daoSession == null) {
				throw new DaoException("Entity is detached from DAO context");
			}
			AssociationDao targetDao = daoSession.getAssociationDao();
			List<Association> associationsNew = targetDao._queryWord_Associations(id);
			synchronized (this) {
				if (associations == null) {
					associations = associationsNew;
				}
			}
		}
		return associations;
	}

	/** Resets a to-many relationship, making the next get call to query for a fresh result. */
	@Generated(hash = 475131113)
	public synchronized void resetAssociations() {
		associations = null;
	}

	/**
	 * To-many relationship, resolved on first access (and after reset).
	 * Changes to to-many relations are not persisted, make changes to the target entity.
	 */
	@Generated(hash = 1015286431)
	public List<Repetition> getRepetitions() {
		if (repetitions == null) {
			final DaoSession daoSession = this.daoSession;
			if (daoSession == null) {
				throw new DaoException("Entity is detached from DAO context");
			}
			RepetitionDao targetDao = daoSession.getRepetitionDao();
			List<Repetition> repetitionsNew = targetDao._queryWord_Repetitions(id);
			synchronized (this) {
				if (repetitions == null) {
					repetitions = repetitionsNew;
				}
			}
		}
		return repetitions;
	}

	/** Resets a to-many relationship, making the next get call to query for a fresh result. */
	@Generated(hash = 605622169)
	public synchronized void resetRepetitions() {
		repetitions = null;
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 128553479)
	public void delete() {
		if (myDao == null) {
			throw new DaoException("Entity is detached from DAO context");
		}
		myDao.delete(this);
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 1942392019)
	public void refresh() {
		if (myDao == null) {
			throw new DaoException("Entity is detached from DAO context");
		}
		myDao.refresh(this);
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 713229351)
	public void update() {
		if (myDao == null) {
			throw new DaoException("Entity is detached from DAO context");
		}
		myDao.update(this);
	}

	/** called by internal mechanisms, do not call yourself. */
	@Generated(hash = 2107838493)
	public void __setDaoSession(DaoSession daoSession) {
		this.daoSession = daoSession;
		myDao = daoSession != null ? daoSession.getWordDao() : null;
	}


}
