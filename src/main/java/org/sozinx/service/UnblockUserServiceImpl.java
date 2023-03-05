package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Block;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.sozinx.constant.ErrorConst.EMAIL_IS_ABSENT;
import static org.sozinx.constant.ErrorConst.USER_IS_NOT_BLOCKED;

public class UnblockUserServiceImpl implements UnblockUserService {
    private final DataBaseService manager;
    @SuppressWarnings("all")
    private static UnblockUserServiceImpl service;

    private Block blockRecord;

    public UnblockUserServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    private boolean emailIsCorrect(final HttpServletRequest req) {
        return manager.getUserManager().getUserByEmail(req.getParameter("unblockEmail")) != null;
    }

    public static synchronized UnblockUserServiceImpl getInstance() {
        if (service == null) return new UnblockUserServiceImpl();
        return service;
    }

    private boolean userIsBlocked(final HttpServletRequest req) {
        List<Block> blockHistory = manager.getBlockManager().getBlockByUser(
                manager.getUserManager().getUserByEmail(req.getParameter("unblockEmail"))); //get records about user block history
        AtomicBoolean isBlocked = new AtomicBoolean(false);
        blockHistory.forEach(block -> {
            if (Objects.equals(block.getUnblock(), "") || block.getUnblock() == null) {
                isBlocked.set(true);
            }
            blockRecord = block;
        }); //checking every record if it is unblocked date(it means that user is in unblock right now)
        return isBlocked.get();
    }

    @Override
    public String validationMessage(final HttpServletRequest req) {
        if (!emailIsCorrect(req)) {
            return EMAIL_IS_ABSENT;
        } else if (!userIsBlocked(req)) {
            return USER_IS_NOT_BLOCKED;
        }
        return null;
    }

    @Override
    public void insertData(HttpServletRequest req) {
        manager.getBlockManager().unblockUser(blockRecord, LocalDate.now().toString());
    }
}
