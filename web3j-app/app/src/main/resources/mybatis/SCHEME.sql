CREATE TABLE `account` (
  `hash` char(42) NOT NULL COMMENT 'hex string of account address hash',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT 'account type, 0: normal; 1: contract',
  `balance` bigint(16) NOT NULL DEFAULT '0' COMMENT 'account balance in Gwei',
  `nonce` int(8) unsigned NOT NULL DEFAULT '0' COMMENT 'nonce',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`hash`),
  KEY `idx_account_balance` (`balance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `block` (
  `number` bigint(16) unsigned NOT NULL COMMENT 'block number/height',
  `hash` char(66) NOT NULL COMMENT 'hex string of block hash',
  `timestamp` datetime NOT NULL COMMENT 'block timestamp',
  `miner` char(42) NOT NULL COMMENT 'hex string of miner address',
  `difficulty` bigint(16) unsigned NOT NULL COMMENT 'difficulty',
  `total_difficulty` bigint(16) unsigned NOT NULL COMMENT 'total difficulty',
  `size` int(8) unsigned NOT NULL COMMENT 'data size in bytes',
  `gas_used` int(8) unsigned NOT NULL COMMENT 'gas used',
  `gas_limit` int(8) unsigned NOT NULL COMMENT 'gas limit',
  `nonce` char(18) NOT NULL COMMENT 'nonce',
  `extra_data` text COMMENT 'extra data',
  `parent_hash` char(66) NOT NULL COMMENT 'hex string of block parent hash',
  `uncle_hash` char(66) NOT NULL COMMENT 'hex string of block uncle hash',
  `state_root` char(66) DEFAULT NULL COMMENT 'state hash root',
  `receipts_root` char(66) DEFAULT NULL COMMENT 'receipts hash root',
  `transactions_root` char(66) DEFAULT NULL COMMENT 'transactions hash root',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`number`),
  UNIQUE KEY `uniq_block_hash` (`hash`),
  KEY `idx_block_miner` (`miner`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `transaction` (
  `hash` char(66) NOT NULL COMMENT 'hex string of transaction hash',
  `block_hash` char(66) NOT NULL COMMENT 'hex string of block hash',
  `block_number` bigint(16) unsigned NOT NULL COMMENT 'block number',
  `from` char(42) NOT NULL COMMENT 'hex string of the sender account address',
  `to` char(42) NOT NULL COMMENT 'hex string of the receiver account address',
  `value` bigint(16) DEFAULT NULL COMMENT 'value of transaction in Gwei',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT 'transaction status, 0: failed; 1: success; ',
  `timestamp` datetime DEFAULT NULL COMMENT 'transaction timestamp',
  `nonce` int(8) unsigned NOT NULL DEFAULT '0' COMMENT 'transaction nonce',
  `transaction_index` int(8) unsigned NOT NULL DEFAULT '0' COMMENT 'transaction position index',
  `type` tinyint(2) DEFAULT '0' COMMENT 'transaction type, 0: normal; 1: contract creation',
  `data` text COMMENT 'transaction data',
  `gas_price` bigint(16) DEFAULT NULL COMMENT 'gas price',
  `gas_limit` int(8) DEFAULT NULL COMMENT 'gas limit',
  `gas_used` int(8) DEFAULT NULL COMMENT 'gas used',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`hash`),
  KEY `idx_transaction_from` (`from`),
  KEY `idx_transaction_to` (`to`),
  KEY `idx_blknumber_txindex` (`block_number`,`transaction_index`),
  KEY `idx_transaction_timestamp` (`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;