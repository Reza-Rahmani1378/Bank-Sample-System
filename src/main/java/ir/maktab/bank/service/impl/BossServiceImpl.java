package ir.maktab.bank.service.impl;

import ir.maktab.bank.base.service.impl.BaseServiceImpl;
import ir.maktab.bank.domain.Boss;
import ir.maktab.bank.repository.BossRepository;
import ir.maktab.bank.service.BossService;

public class BossServiceImpl extends BaseServiceImpl<Boss, Long, BossRepository>
        implements BossService {
    public BossServiceImpl(BossRepository repository) {
        super(repository);
    }
}
