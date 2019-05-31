package tech.fiszki.data;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@Entity(nameInDb = "association")
public class Association{

    @Id(autoincrement = true)
    @Property(nameInDb = "association_id")
    private Long id;

    private String associationWord;
    @ToOne(joinProperty = "wordId")
    private Word word;

    @Property(nameInDb = "fk_word_id")
    private Long wordId;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 2041689587)
    private transient AssociationDao myDao;



    @Generated(hash = 638819578)
    public Association(Long id, String associationWord, Long wordId) {
        this.id = id;
        this.associationWord = associationWord;
        this.wordId = wordId;
    }


    @Generated(hash = 1461973931)
    public Association() {
    }

    @Generated(hash = 1683684945)
    private transient Long word__resolvedKey;



    public String getAssociationWord() {
        return associationWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAssociationWord(String associationWord) {
        this.associationWord = associationWord;
    }

    public Long getWordId() {
        return this.wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1291809355)
    public Word getWord() {
        Long __key = this.wordId;
        if (word__resolvedKey == null || !word__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            WordDao targetDao = daoSession.getWordDao();
            Word wordNew = targetDao.load(__key);
            synchronized (this) {
                word = wordNew;
                word__resolvedKey = __key;
            }
        }
        return word;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 216030285)
    public void setWord(Word word) {
        synchronized (this) {
            this.word = word;
            wordId = word == null ? null : word.getId();
            word__resolvedKey = wordId;
        }
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
    @Generated(hash = 517830943)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAssociationDao() : null;
    }


}
