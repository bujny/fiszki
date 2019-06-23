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
@Entity(nameInDb = "review_algorithm")
public class ReviewAlgorithm  {

    @Id(autoincrement = true)
    @Property(nameInDb = "review_algorithm_id")
    private Long id;

    private String name;
    @ToMany(referencedJoinProperty = "reviewAlgorithmId")
    private List<ReviewDay> reviewDays;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 915718801)
    private transient ReviewAlgorithmDao myDao;


    @Generated(hash = 376611293)
    public ReviewAlgorithm(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1401972244)
    public ReviewAlgorithm() {
    }


    public int getExpectedReviewDays(int daysFromReviewStart){
        int count = 0;
//        //TODO --> fix many relationship
//        for (ReviewDay reviewDay : Queries.getReviewDaysForReviewAlgorithm(this)) {
//            if(reviewDay.getDaysAfterBeginning() <= daysFromReviewStart){
//                count++;
//            }
//        }
        return count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReviewDays(List<ReviewDay> reviewDays) {
        this.reviewDays = reviewDays;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 263983215)
    public List<ReviewDay> getReviewDays() {
        if (reviewDays == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ReviewDayDao targetDao = daoSession.getReviewDayDao();
            List<ReviewDay> reviewDaysNew = targetDao._queryReviewAlgorithm_ReviewDays(id);
            synchronized (this) {
                if (reviewDays == null) {
                    reviewDays = reviewDaysNew;
                }
            }
        }
        return reviewDays;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1861144975)
    public synchronized void resetReviewDays() {
        reviewDays = null;
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
    @Generated(hash = 872919835)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getReviewAlgorithmDao() : null;
    }

}
