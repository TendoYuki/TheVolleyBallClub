<?php

namespace Routing;

use Authorization\AuthorizationLevel;

class DashboardRouter extends AbstractRouter{
    public function __construct() {
        parent::__construct();
        $this->newRoute('', '@Views/dashboard/user/profile.php', AuthorizationLevel::User);
        $this->newRoute('', '@Views/dashboard/admin/members/members.php', AuthorizationLevel::Admin);


        $this->newRoute('/profile', '@Views/dashboard/user/profile.php', AuthorizationLevel::User);
        $this->newRoute('/profile/edit', '@Views/dashboard/user/edit.php', AuthorizationLevel::User);
        $this->newRoute('/profile/edit/submit', '@Controllers/UserController.php', AuthorizationLevel::User);
        $this->newRoute('/documents', '@Views/dashboard/user/documents.php', AuthorizationLevel::User);
        $this->newRoute('/trainings', '@Views/dashboard/user/trainings.php', AuthorizationLevel::User);
        $this->newRoute('/competitions', '@Views/dashboard/user/competitions.php', AuthorizationLevel::User);
        
        $this->newRoute('/members', '@Views/dashboard/admin/members/members.php', AuthorizationLevel::Admin);
        $this->newRoute('/members/view', '@Views/dashboard/admin/members/view.php', AuthorizationLevel::Admin);
        $this->newRoute('/members/edit', '@Views/dashboard/admin/members/edit.php', AuthorizationLevel::Admin);
    }
}