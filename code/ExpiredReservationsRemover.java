import java.util.TimerTask;

public class ExpiredReservationsRemover extends TimerTask {
    private ReservationMgr reservationMgr;

    public ExpiredReservationsRemover(ReservationMgr reservationMgr) {
        this.reservationMgr = reservationMgr;
    }

    @Override
    public void run() {
        System.out.println("Debug: ExpiredReservationsRemover.run() executed.");
        reservationMgr.removeNoShowReservations();
    }
}
