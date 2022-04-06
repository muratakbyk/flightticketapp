package flight.flightticketapp.business.abstracts;

import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;

import java.util.List;

public interface BaseEntityAbstract<T> {

    Result add(T entity);

    Result delete(int id);

    DataResult<T> getById(int id);

}
