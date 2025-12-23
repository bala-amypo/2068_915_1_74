@Service
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final ClaimRepository claimRepository;
    private final FraudRuleRepository fraudRuleRepository;
    private final FraudCheckResultRepository resultRepository;

    public FraudDetectionServiceImpl(
            ClaimRepository claimRepository,
            FraudRuleRepository fraudRuleRepository,
            FraudCheckResultRepository resultRepository) {

        this.claimRepository = claimRepository;
        this.fraudRuleRepository = fraudRuleRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public FraudCheckResult evaluateClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));

        List<FraudRule> rules = fraudRuleRepository.findAll();

        boolean isFraudulent = !rules.isEmpty();
        String triggeredRuleName = isFraudulent ? rules.get(0).getRuleName() : null;
        String rejectionReason = isFraudulent
                ? "Rule triggered: " + triggeredRuleName
                : null;

        FraudCheckResult result = new FraudCheckResult();
        result.setClaim(claim);
        result.setIsFraudulent(isFraudulent);
        result.setTriggeredRuleName(triggeredRuleName);
        result.setRejectionReason(rejectionReason);
        result.setCheckedAt(LocalDateTime.now());
        result.setMatchedRules(new HashSet<>(rules));

        claim.setFraudCheckResult(result);

        return resultRepository.save(result);
    }

    @Override
    public FraudCheckResult getResultByClaim(Long claimId) {
        return resultRepository.findByClaimId(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}
