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
@Entity(nameInDb = "review_day")
public class ReviewDay  {


    @Id(autoincrement = true)
    @Property(nameInDb = "review_day_id")
    private Long id;

    private int daysAfterBeginning;
    @ToOne(joinProperty = "reviewAlgorithmId")
    private ReviewAlgorithm reviewAlgorithm;

    @Property(nameInDb = "fk_review_algorithm_id")
    private Long reviewAlgorithmId;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1435514569)
    private transient ReviewDayDao myDao;

    @Generated(hash = 433291956)
    private transient Long reviewAlgorithm__resolvedKey;



    public ReviewDay(Long id, int daysAfterBeginning) {
        this.id = id;
        this.daysAfterBeginning = daysAfterBeginning;
    }

    public ReviewDay() {
    }

    @Generated(hash = 1357768488)
    public ReviewDay(Long id, int daysAfterBeginning, Long reviewAlgorithmId) {
        this.id = id;
        this.daysAfterBeginning = daysAfterBeginning;
        this.reviewAlgorithmId = reviewAlgorithmId;
    }



    public int getDaysAfterBeginning() {
        return daysAfterBeginning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDaysAfterBeginning(int daysAfterBeginning) {
        this.daysAfterBeginning = daysAfterBeginning;
    }

    public Long getReviewAlgorithmId() {
        return this.reviewAlgorithmId;
    }

    public void setReviewAlgorithmId(Long reviewAlgorithmId) {
        this.reviewAlgorithmId = reviewAlgorithmId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1599191927)
    public ReviewAlgorithm getReviewAlgorithm() {
        Long __key = this.reviewAlgorithmId;
        if (reviewAlgorithm__resolvedKey == null
                || !reviewAlgorithm__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ReviewAlgorithmDao targetDao = daoSession.getReviewAlgorithmDao();
            ReviewAlgorithm reviewAlgorithmNew = targetDao.load(__key);
            synchronized (this) {
                reviewAlgorithm = reviewAlgorithmNew;
                reviewAlgorithm__resolvedKey = __key;
            }
        }
        return reviewAlgorithm;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1455896421)
    public void setReviewAlgorithm(ReviewAlgorithm reviewAlgorithm) {
        synchronized (this) {
            this.reviewAlgorithm = reviewAlgorithm;
            reviewAlgorithmId = reviewAlgorithm == null ? null
                    : reviewAlgorithm.getId();
            reviewAlgorithm__resolvedKey = reviewAlgorithmId;
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
    @Generated(hash = 2033465520)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getReviewDayDao() : null;
    }


}
