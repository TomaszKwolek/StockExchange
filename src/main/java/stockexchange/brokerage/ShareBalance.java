package stockexchange.brokerage;


public class ShareBalance {

		private String  companyCode;
		private Integer amount;
		
		public ShareBalance() {
			super();
		}

		public ShareBalance(String companyCode, Integer amount) {
			super();
			this.companyCode = companyCode;
			this.amount = amount;
		}

		public String getCompanyCode() {
			return companyCode;
		}

		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}
				
}
