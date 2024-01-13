<?php
    namespace Components\Navbar;
    enum AdminNavbarEntry: int{
        case members = 1;
        case admins = 2;
        case competitions = 3;
        case trainings = 4;

    }
    class AdminNavbar extends AbstractDashboardNavbar{
        public function __construct(AdminNavbarEntry $active) {
            parent::__construct($active, array(
                AdminNavbarEntry::trainings->value => array(
                    "symbol" => get_public_file("symbols/training-symbol.svg"),
                    "link" => "/dashboard/trainings",
                    "link_text" => "Entrainements"
                ),
                AdminNavbarEntry::competitions->value => array(
                    "symbol" => get_public_file("symbols/competition-symbol.svg"),
                    "link" => "/dashboard/competitions",
                    "link_text" => "Competitions"
                ),
                AdminNavbarEntry::members->value => array(
                    "symbol" => get_public_file("symbols/user-symbol.svg"),
                    "link" => "/dashboard/members",
                    "link_text" => "Membres"
                ),
                AdminNavbarEntry::admins->value => array(
                    "symbol" => get_public_file("symbols/admin-symbol.svg"),
                    "link" => "/dashboard/admins",
                    "link_text" => "Administrateurs"
                )
            ));
        }
    }
?>
