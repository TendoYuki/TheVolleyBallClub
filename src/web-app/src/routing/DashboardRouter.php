<?php

namespace Routing;

use Authorization\AuthorizationLevel;

class DashboardRouter extends AbstractRouter{
    public function __construct() {
        parent::__construct();
        $this->newRoute('', '@Views/dashboard/user/user-dashboard.php', AuthorizationLevel::User);
        $this->newRoute('', '@Views/dashboard/admin/members/members.php', AuthorizationLevel::Admin);
        $this->newRoute('/members', '@Views/dashboard/admin/members/members.php', AuthorizationLevel::Admin);
        $this->newRoute('/members/view', '@Views/dashboard/admin/members/view.php', AuthorizationLevel::Admin);
        $this->newRoute('/members/edit', '@Views/dashboard/admin/members/edit.php', AuthorizationLevel::Admin);
    }
}