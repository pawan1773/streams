import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SumExample {
	
	public static boolean isNotNull(Object object) {
		return null != object;
	}

	public static void main(String[] args) {
		
		List<ContractMapping> contractMappings = new ArrayList<>();
		
		contractMappings
				.add(new  ContractMapping(new CampaignMaster("Open"), BigDecimal.valueOf(1001), BigDecimal.valueOf(1002), null));
		contractMappings
				.add(new ContractMapping(new CampaignMaster("Open"), BigDecimal.valueOf(2001), BigDecimal.valueOf(2002), BigDecimal.valueOf(2003)));
		contractMappings
				.add(new ContractMapping(new CampaignMaster("Closed"), BigDecimal.valueOf(3001), BigDecimal.valueOf(3002), BigDecimal.valueOf(3003)));
		contractMappings
				.add(new ContractMapping(new CampaignMaster("Open"), BigDecimal.valueOf(4001), BigDecimal.valueOf(4002), BigDecimal.valueOf(4003)));

		final BigDecimal totalMaturedAmount = contractMappings
												.stream()
												.map(ContractMapping::getMaturedAmount)
												.filter(SumExample::isNotNull)
												.reduce(BigDecimal.ZERO, BigDecimal::add);
		final BigDecimal totalPaidAmount = contractMappings
											.stream()
											.map(ContractMapping::getPaidAmount)
											.filter(amount -> amount != null)
											.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		final BigDecimal missedAmount = contractMappings.stream()
				.filter(contractMapping -> contractMapping.getCampaignMaster().getStatus().equalsIgnoreCase("Closed")).map(ContractMapping::getMissedAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

		System.out.println("totalMaturedAmount => " + totalMaturedAmount);
		System.out.println("totalPaidAmount => " + totalPaidAmount);
		System.out.println("missedAmount => " + missedAmount);
	}

}

class ContractMapping {
	private BigDecimal missedAmount;
	private BigDecimal paidAmount;
	private BigDecimal maturedAmount;

	private CampaignMaster campaignMaster;

	public ContractMapping(CampaignMaster campaignMaster, BigDecimal potentialAmount, BigDecimal paidAmount,
			BigDecimal maturedAmount) {
		this.campaignMaster = campaignMaster;
		this.missedAmount = potentialAmount;
		this.paidAmount = paidAmount;
		this.maturedAmount = maturedAmount;
	}

	public CampaignMaster getCampaignMaster() {
		return campaignMaster;
	}

	public void setCampaignMaster(CampaignMaster campaignMaster) {
		this.campaignMaster = campaignMaster;
	}

	public BigDecimal getMissedAmount() {
		return missedAmount;
	}

	public void setMissedAmount(BigDecimal missedAmount) {
		this.missedAmount = missedAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getMaturedAmount() {
		return maturedAmount;
	}

	public void setMaturedAmount(BigDecimal maturedAmount) {
		this.maturedAmount = maturedAmount;
	}

}

class CampaignMaster {

	public CampaignMaster(String status) {
		this.status = status;
	}

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
