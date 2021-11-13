import java.util.TimerTask;

/**
 * Represents a {@link java.util.TimerTask} object which can be scheduled to be executed by a timer.
 *
 * @since 2021-11-5
 */
public class ExpiredReservationsRemover extends TimerTask {
    /**
     * The same {@link ReservationMgr} in our application
     */
    private final ReservationMgr reservationMgr;

    /**
     * Class constructor.
     *
     * @param reservationMgr The reservation manager whose method would be called by this class.
     */
    public ExpiredReservationsRemover(ReservationMgr reservationMgr) {
        this.reservationMgr = reservationMgr;
    }

    /**
     * Action that will be executed by the timer task. This removes {@link Reservation} upon period expiry which is set
     * at 15 minutes.
     */
    @Override
    public void run() {
        reservationMgr.removeNoShowReservations();
    }
}
