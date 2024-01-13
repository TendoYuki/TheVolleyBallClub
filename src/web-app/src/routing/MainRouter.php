<?php

namespace Routing;

use Authorization\AuthorizationLevel;

class MainRouter extends AbstractRouter{
    public function __construct() {
        parent::__construct();
        $this->newRoute('', '@Views/home.php', AuthorizationLevel::Any);
        $this->newRoute('/', '@Views/home.php', AuthorizationLevel::Any);
        $this->newRoute('/sign-in', '@Views/connection/sign-in.php', AuthorizationLevel::Guest);
        $this->newRoute('/sign-in/submit', '@Controllers/SignInController.php', AuthorizationLevel::Guest);
        $this->newRoute('/sign-up', '@Views/connection/sign-up.php', AuthorizationLevel::Guest);
        $this->newRoute('/sign-up/submit', '@Controllers/UserController.php', AuthorizationLevel::Guest);
        $this->newRoute('/sign-out', '@Controllers/SignOutController.php', AuthorizationLevel::LoggedIn);
        $this->newRoute('/reset-password', '@Views/connection/reset-password.php', AuthorizationLevel::Guest);
        $this->newRoute('/contact', '@Views/contact.php', AuthorizationLevel::Any);
        $this->newRoute('/info', '@Views/informations.php', AuthorizationLevel::Any);
        $this->newRouteDelegation('/dashboard', DashboardRouter::class, AuthorizationLevel::LoggedIn);
        $this->newRouteDelegation('/planning', PlanningRouter::class, AuthorizationLevel::Any);
        $this->new404Handler('@Views/error-pages/404.php');
    }
}