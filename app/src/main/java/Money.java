import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by mirai on 2014/11/27.
 */
@DatabaseTable(tableName="Money")
public class Money {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private Date date;
    @DatabaseField
    private Integer money;

        public Money(Integer id, Date date, Integer money) {
            this.id = id;
            this.date = date;
            this.money = money;
        }

        public Money(Date date, Integer money) {
            this.date = date;
            this.money = money;
        }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Integer getMoney() {
        return money;
    }
}

