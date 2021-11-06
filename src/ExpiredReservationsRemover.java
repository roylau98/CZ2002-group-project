import java.util.TimerTask;

/**
 * Represents a TimerTask object which can be scheduled to be executed by a timer.
 *
 * @author
 * @since 2021-11-5
 */
public class ExpiredReservationsRemover extends TimerTask {
    /**
     * The same reservationMgr in our application
     */
    private final ReservationMgr reservationMgr;

    /**
     * Constructor for a new TimerTask which initialize the reservation manager.
     *
     * @param reservationMgr
     */
    public ExpiredReservationsRemover(ReservationMgr reservationMgr) {
        this.reservationMgr = reservationMgr;
    }

    /**
     * Action that will be executed by the timer task. This removes reservations upon period expiry which is set
     * at 15 minutes.
     */
    @Override
    public void run() {
        reservationMgr.removeNoShowReservations();
    }
}
