package tech.fiszki.data;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;


@AllArgsConstructor
@Builder
@Entity(nameInDb = "repetition")
public class Repetition {

    @Id(autoincrement = true)
    @Property(nameInDb = "repetition_id")
    private Long id;

    @Property(nameInDb = "repetition_date")
    private Date repetitionDate;

    private double successRate;

    @ToOne(joinProperty = "wordId")
    private Word word;

    @Property(nameInDb = "fk_word_id")
    private Long wordId;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 891294223)
    private transient RepetitionDao myDao;


    @Generated(hash = 648210394)
    public Repetition(Long id, Date repetitionDate, double successRate,
            Long wordId) {
        this.id = id;
        this.repetitionDate = repetitionDate;
        this.successRate = successRate;
        this.wordId = wordId;
    }

    @Generated(hash = 985444115)
    public Repetition() {
    }

    @Generated(hash = 1683684945)
    private transient Long word__resolvedKey;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRepetitionDate() {
        return this.repetitionDate;
    }

    public void setRepetitionDate(Date repetitionDate) {
        this.repetitionDate = repetitionDate;
    }

    public double getSuccessRate() {
        return this.successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
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
    @Generated(hash = 1989026243)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRepetitionDao() : null;
    }



}
