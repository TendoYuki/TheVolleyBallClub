<?php require_once("/srv/http/endpoint/app-config.php") ?>
<script src="/components/navbar/dashboard-navbar.js" defer></script>

<?php
    enum AdminNavbarEntry{
        case members;
        case admins;
        case competitions;
        case trainings;
        case none;

    }
    class AdminNavbar {
        private $active;
        public function __construct($active) {
            $this->active = $active;
        }

        public function display() {
            echo(
                '
                <ul class="admin-navbar">
                    <li '.($this->active==AdminNavbarEntry::trainings ? 'class="active"' : '').'>
                        '.file_get_contents(PUBLIC_PATH."symbols/training-symbol.svg").'
                        <a href="/dashboard/admin/trainings">Entrainements</a>
                    </li>
                    <li '.($this->active==AdminNavbarEntry::competitions ? 'class="active"' : '').'>
                        '.file_get_contents(PUBLIC_PATH."symbols/competition-symbol.svg").'
                        <a href="/dashboard/admin/competitions">Compétitions</a>
                    </li>
                    <li '.($this->active==AdminNavbarEntry::members ? 'class="active"' : '').'>
                        '.file_get_contents(PUBLIC_PATH."symbols/user-symbol.svg").'
                        <a href="/dashboard/admin/members">Membres</a>
                    </li>
                    <li '.($this->active==AdminNavbarEntry::admins ? 'class="active"' : '').'>
                        '.file_get_contents(PUBLIC_PATH."symbols/admin-symbol.svg").'
                        <a href="/dashboard/admin/admins">Administrateurs</a>
                    </li>
                </ul>
                '
            );
        }
    }
?>
