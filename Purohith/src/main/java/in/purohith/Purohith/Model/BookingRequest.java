package in.purohith.Purohith.Model;

public class BookingRequest {
	 private Long userId;
	    private Long priestAvailabilityId;
	    private Long poojaTypeId;
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public Long getPriestAvailabilityId() {
			return priestAvailabilityId;
		}
		public void setPriestAvailabilityId(Long priestAvailabilityId) {
			this.priestAvailabilityId = priestAvailabilityId;
		}
		public Long getPoojaTypeId() {
			return poojaTypeId;
		}
		public void setPoojaTypeId(Long poojaTypeId) {
			this.poojaTypeId = poojaTypeId;
		}

}
