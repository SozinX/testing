package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Block;
import org.sozinx.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.sozinx.constant.ErrorConst.*;

public class BlockUserServiceImpl implements BlockUserService {
    private final DataBaseService manager;
    @SuppressWarnings("all")
    private static BlockUserServiceImpl service;

    public BlockUserServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    private boolean emailIsCorrect(final HttpServletRequest req) {
        return manager.getUserManager().getUserByEmail(req.getParameter("blockEmail")) != null;
    }

    public static synchronized BlockUserServiceImpl getInstance() {
        if (service == null) return new BlockUserServiceImpl();
        return service;
    }

    private boolean userIsBlocked(final HttpServletRequest req) {
        List<Block> blockHistory = manager.getBlockManager().getBlockByUser(
                manager.getUserManager().getUserByEmail(req.getParameter("blockEmail")));
        AtomicBoolean isBlocked = new AtomicBoolean(false);
        blockHistory.forEach(block -> {
            if (Objects.equals(block.getUnblock(), "") || block.getUnblock() == null) {
                isBlocked.set(true);
            }
        });
        return isBlocked.get();
    }

    @Override
    public String inputIsCorrect(final HttpServletRequest req) {
        if (!emailIsCorrect(req)) {
            return EMAIL_IS_ABSENT;
        } else if (userIsBlocked(req)) {
            return USER_IS_BLOCKED;
        }
        return null;
    }

    @Override
    public void blockUser(HttpServletRequest req) {
        User teacher = manager.getUserManager().getUserById(Long.parseLong(req.getSession().getAttribute("id").toString()));
        User student = manager.getUserManager().getUserByEmail(req.getParameter("blockEmail"));
        manager.getBlockManager().blockUser(new Block(0, teacher, student, LocalDate.now().toString(), null));
    }
}
